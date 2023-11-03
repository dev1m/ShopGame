package net.dev1m.shopgame.service.imp;

import net.dev1m.shopgame.entity.Options;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface OptionServiceImp {
    boolean updateOptions(Map<String, String> params);
    String getOptionValue(String option_key);
}
