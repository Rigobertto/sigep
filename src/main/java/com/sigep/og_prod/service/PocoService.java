package com.sigep.og_prod.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sigep.og_prod.model.Poco;
import com.sigep.og_prod.repository.PocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class PocoService {

    @Autowired
    private PocoRepository pocoRepository;

    public void salvarDadosPlanilha(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("O arquivo está vazio.");
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            CsvToBean<Poco> csvToBean = new CsvToBeanBuilder<Poco>(reader)
                    .withType(Poco.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build();

            List<Poco> pocosBatch = new ArrayList<>();
            int batchSize = 1000;

            for (Poco poco : csvToBean) {
                pocosBatch.add(poco);
                if (pocosBatch.size() == batchSize) {
                    pocoRepository.saveAll(pocosBatch);
                    pocosBatch.clear();
                }
            }

            if (!pocosBatch.isEmpty()) {
                pocoRepository.saveAll(pocosBatch);
            }
        }
    }

    public Page<Poco> listarTodos(String poco, String estado, String bacia, String operador, Pageable pageable) {
        String filtroPoco = (poco == null || poco.trim().isEmpty()) ? "%%" : "%" + poco.toLowerCase() + "%";
        String filtroOperador = (operador == null || operador.trim().isEmpty()) ? "%%"
                : "%" + operador.toLowerCase() + "%";
        return pocoRepository.findByFiltros(filtroPoco, estado, bacia, filtroOperador, pageable);
    }
}
