package br.com.diego.mvc.mudi.api;

import br.com.diego.mvc.mudi.interceptor.AccessInterceptor;
import br.com.diego.mvc.mudi.interceptor.AccessInterceptor.Access;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/access")
public class AccessRest {

    @GetMapping
    public List<Access> getAccess() {
        return AccessInterceptor.accesses;
    }
}
