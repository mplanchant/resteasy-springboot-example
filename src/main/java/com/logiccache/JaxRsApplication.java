package com.logiccache;

import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Component
@ApplicationPath("/test-app/")
public class JaxRsApplication extends Application {
}
