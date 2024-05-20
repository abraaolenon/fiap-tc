package br.com.fiap.produto.v1.controller;

import java.util.Collection;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.produto.core.service.ProdutoService;
import br.com.fiap.produto.v1.dto.ProdutoDTO;
import br.com.fiap.produto.v1.dto.ProdutoRespostaDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/produtos")
@OpenAPIDefinition(info = @Info(title = "Api de Produtos para o IV Tech Challenge"))
@Tag(name = "Microsserviço de Catálogo de Produtos", description = "Este microsserviço gerenciará o catálogo de produtos, incluindo informações detalhadas dos produtos e o controle de estoque. Uma característica chave será a funcionalidade de carga de produtos, permitindo a importação em massa de informações de produtos para o sistema. Esta funcionalidade específica permitirá a importação em massa de dados de produtos, incluindo descrições, preços e quantidades em estoque.")
public class ProdutoController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("csvJob")
    private Job job;

    @Autowired
    private ProdutoService produtoService;

    @Operation(description = "Consulta de todos os produtos.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ProdutoRespostaDTO>> buscarTodos() throws InterruptedException {

        return ResponseEntity.ok(produtoService.buscarTodos());
    }

    @Operation(description = "Insere um produto no estoque.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoRespostaDTO> inserir(ProdutoDTO produtoDTO) {

        return new ResponseEntity<>(produtoService.inserir(produtoDTO), HttpStatus.CREATED);

    }

    @Operation(description = "Consulta de um produto do estoque.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoRespostaDTO> buscar(@PathVariable Integer id) throws InterruptedException {

        return ResponseEntity.ok(produtoService.buscar(id));
    }

    @Operation(description = "Deleta uma produto do estoque.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso") })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletar(@PathVariable Integer id) {

        produtoService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }

    @Operation(description = "Executa o Batch.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Executado com sucesso") })
    @PostMapping(path = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> startBatch() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();

        JobExecution run = null;
        try {
            run = jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(run.getStatus().toString());

    }

}
