package com.socs.socs.service.impl;
import com.socs.socs.entity.Socks;
import com.socs.socs.repo.SocksRepos;
import com.socs.socs.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SocksServiceImpl implements SocksService {
private SocksRepos socksRepos;
    @Autowired
    public SocksServiceImpl(SocksRepos socksRepos) {
        this.socksRepos = socksRepos;
    }
    @Override
    @Transactional
    public Socks addSocks(String color,int cottonPart,int quantity) {
        Socks socks = createSocks(color,cottonPart,quantity);

        if (socksRepos.exists(color,cottonPart )>0) {
            socksRepos.SetCountSocks(color, cottonPart, socksRepos.getCountSocks(color, cottonPart) + quantity);
        }
        else {
            return socksRepos.save(socks);
        }
       socks.setQuantity(socksRepos.getCountSocks(color, cottonPart));
        return socks;
    }

    @Override
    @Transactional
    public Socks delSocks(String color,int cottonPart,int quantity) {
        Socks socks = createSocks(color,cottonPart,quantity);
        if (socksRepos.exists(color,cottonPart )>0) {
            if (socksRepos.getCountSocks(color, cottonPart) - quantity >= 0) {
                socksRepos.SetCountSocks(color, cottonPart,socksRepos.getCountSocks(color, cottonPart) - quantity);
                socks.setQuantity(socksRepos.getCountSocks(color, cottonPart));
            }
            else
                socks.setQuantity(socksRepos.getCountSocks(color, cottonPart) - quantity);

        }
       //  System.out.println(socksRepos.getCountSocks(color,cottonPart));

        return socks;
   }
    @Override
    @Transactional
    public Integer getSocks(String color,int cottonPart, String operation) {

        switch (operation) {
            case ("moreThan"): {
                return socksRepos.operMoreThen(color,cottonPart);
            }
            case ("lessThan"): {
                return socksRepos.operLessThen(color,cottonPart);
            }
            case ("equal"): {
                return socksRepos.operEqual(color,cottonPart);
            }
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверная операция");
        }

        }
        //  System.out.println(socksRepos.getCountSocks(color,cottonPart));

    private Socks createSocks(String color,int cottonPart,int quantity)
    {
        Socks socks = new Socks();
        socks.setColor(color);
        socks.setCottonPart(cottonPart);
        socks.setQuantity(quantity);
        return socks;
    }
}
