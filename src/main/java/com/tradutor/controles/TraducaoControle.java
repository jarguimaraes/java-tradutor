package com.tradutor.controles;

import com.tradutor.entidades.Config;
import com.tradutor.servicos.ServicoTraducao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tradutor")
public class TraducaoControle {

    @Autowired
    private ServicoTraducao servico;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    Config traduzir(@RequestBody Config cfg){
        try{
            return servico.traduzir(cfg);
        }catch (Exception e){
            Logger.getLogger(TraducaoControle.class.getName()).log(Level.SEVERE, "Erro ao tentar traduzir", e);
            return new Config();
        }
    }

}
