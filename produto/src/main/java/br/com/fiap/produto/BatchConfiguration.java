package br.com.fiap.produto;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.fiap.produto.core.entity.Produto;
import br.com.fiap.produto.core.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class BatchConfiguration {
    private final ProdutoRepository produtoRepository;

    public FlatFileItemReader<Produto> itemReader() {
        FlatFileItemReader<Produto> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/produtos.csv"));
        itemReader.setName("csv-reader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @SuppressWarnings("unchecked")
    private LineMapper<Produto> lineMapper() {
        DefaultLineMapper<Produto> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("descricao", "preco","quantidade");
        tokenizer.setStrict(false);

        @SuppressWarnings("rawtypes")
        BeanWrapperFieldSetMapper mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(Produto.class);

        lineMapper.setFieldSetMapper(mapper);
        lineMapper.setLineTokenizer(tokenizer);
        return lineMapper;
    }

    @Bean
    public ProdutoProcessor processor() {
        return new ProdutoProcessor();
    }

    @Bean
    public RepositoryItemWriter<Produto> itemWriter() {
        RepositoryItemWriter<Produto> writer = new RepositoryItemWriter<>();
        writer.setRepository(produtoRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("csv-step", repository)
                .<Produto, Produto>chunk(10, transactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    private TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

    @Bean(name = "csvJob")
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("csv-job", jobRepository)
                .flow(step(jobRepository, transactionManager)).end().build();
    }

}
