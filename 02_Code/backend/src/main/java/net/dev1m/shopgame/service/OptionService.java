package net.dev1m.shopgame.service;

import net.dev1m.shopgame.entity.Options;
import net.dev1m.shopgame.reponsitory.OptionReponsitory;
import net.dev1m.shopgame.service.imp.OptionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OptionService implements OptionServiceImp {
    @Autowired
    OptionReponsitory optionReponsitory;

    @Override
    public boolean updateOptions(Map<String, String> params) {
        try {
            for (String key : params.keySet()) {
                String value = params.get(key);
                Options options = optionReponsitory.findByOptionKey(key);
                if (options != null) {
                    options.setOption_value(value);
                    optionReponsitory.save(options);
                }
                else {
                    Options options1 = new Options();
                    options1.setOption_key(key);
                    options1.setOption_value(value);
                    optionReponsitory.save(options1);
                }
            }
            return true; // Thao tác thành công
        } catch (Exception e) {
            System.out.println("Error add option "+ e.getMessage());
            return false; // Thao tác không thành công
        }
    }

    @Override
    public String getOptionValue(String option_key) {
        List<Options> options = optionReponsitory.findAll();
        for (Options data : options) {
            if (data.getOption_key().equals(option_key)) {
                return data.getOption_value();
            }
        }
        return "";
    }
}
