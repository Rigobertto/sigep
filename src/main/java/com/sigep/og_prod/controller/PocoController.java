package com.sigep.og_prod.controller;

import com.sigep.og_prod.model.Poco;
import com.sigep.og_prod.service.PocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@RequestMapping("/api/pocos")
@CrossOrigin(origins = "*")
@Tag(name = "Poços", description = "Endpoints para gerenciamento e importação de dados de Poços da ANP")
public class PocoController {

    @Autowired
    private PocoService pocoService;

    @Operation(summary = "Importar Planilha CSV", description = "Recebe um arquivo CSV (multipart/form-data) contendo os dados dos poços e os insere no banco de dados.", responses = {
            @ApiResponse(responseCode = "200", description = "Arquivo importado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao processar o arquivo")
    })
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadPlanilha(
            @Parameter(description = "Arquivo CSV com os dados da ANP", required = true) @RequestParam("file") MultipartFile file) {
        try {
            pocoService.salvarDadosPlanilha(file);
            return ResponseEntity.ok(Map.of("mensagem", "Arquivo importado com sucesso!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Erro ao importar arquivo: " + e.getMessage()));
        }
    }

    @Operation(summary = "Listar Poços", description = "Retorna uma lista paginada de poços com filtros opcionais. Os filtros 'poco' e 'operador' suportam busca parcial e ignoram maiúsculas/minúsculas.")
    @GetMapping
    public ResponseEntity<Page<Poco>> listarPocos(
            @Parameter(description = "Filtro pelo código/nome do poço (ex: 1-BRSA)") @RequestParam(required = false) String poco,
            @Parameter(description = "Filtro exato pela sigla do estado (ex: RJ, SP, RN)") @RequestParam(required = false) String estado,
            @Parameter(description = "Filtro exato pelo nome da bacia (ex: Campos)") @RequestParam(required = false) String bacia,
            @Parameter(description = "Filtro pelo nome da operadora (ex: PETROBRAS)") @RequestParam(required = false) String operador,
            @Parameter(description = "Parâmetros de paginação e ordenação") Pageable pageable) {
        Page<Poco> pocos = pocoService.listarTodos(poco, estado, bacia, operador, pageable);
        return ResponseEntity.ok(pocos);
    }
}
