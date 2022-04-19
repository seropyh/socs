package com.socs.socs.control;

import com.socs.socs.dto.SocksDto;
import com.socs.socs.entity.Socks;
import com.socs.socs.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class SocksControl {
    private SocksService socksService;

    @Autowired
    public SocksControl(SocksService socksService) {
        this.socksService = socksService;
    }

    @RequestMapping( value ="/api/socks/income", method = RequestMethod.POST)
    public ResponseEntity<Socks> addSocks(@Valid @RequestBody SocksDto socksDto) {
        Socks socks = socksService.addSocks((socksDto.getColor()),socksDto.getCottonPart(), socksDto.getQuantity());
        return  ResponseEntity.ok(socks);
    }

    @RequestMapping(value ="/api/socks/outcome", method = RequestMethod.POST)
    public ResponseEntity<Socks> delSocks(@RequestBody SocksDto socksDto) {
        Socks socks = socksService.delSocks((socksDto.getColor()),socksDto.getCottonPart(), socksDto.getQuantity());
        if (socks.getQuantity()>=0)
        return  ResponseEntity.ok(socks);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Недостаточно носков данного типа");
    }
    @RequestMapping(value ="/api/socks", method = RequestMethod.GET,params = {"color", "operation","cottonPart"})
    public Integer getSocks(@RequestParam(value = "color") String color,
                            @RequestParam(value = "operation") String operation,
                            @RequestParam(value = "cottonPart") int cottonPart) {
       return socksService.getSocks(color,cottonPart,operation);
    }


}

