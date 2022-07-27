package «pkg».cockpit.repo;

import «pkg».cockpit.dto.web.monitor.DashboardDto;
import «pkg».cockpit.mapper.DashboardMapper;
import «pkg».cockpit.model.monitor.Dashboard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static «pkg».cockpit.util.Utils.runCatching;

@Component
public class DashboardRepo {

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private DashboardMapper mapper;

    private File directory;

    @Value("${monitors.directory}")
    public void setDirectory(String directory) {
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.directory = file;
    }

    public List<Dashboard> getAll() {
        return Stream.of(directory.listFiles())
                .map(f ->  {
                    /*try {
                        Monitor dashboard = jsonMapper.readValue(f, Monitor.class);
                        return dashboard;
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    } catch (JsonMappingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;*/
                    //runCatching(() -> jsonMapper.readValue(f, Monitor.class))
                    return readFile(f);
                })
                .filter(m -> m != null)
                .collect(Collectors.toList());
    }

    public void add(Dashboard dashboard) {
        writeFile(dashboard);
    }

    public Dashboard get(String id) {
        return readFile(getFile(id));
    }

    public void update(Dashboard dashboard) {
        writeFile(dashboard);
    }

    public void delete(String id) {
        getFile(id).delete();
    }


    private File getFile(String id) {
        return new File(this.directory.getAbsolutePath() + "/" + id + ".json");
    }

    private void writeFile(Dashboard dashboard) {
        try {
            DashboardDto dto = mapper.map(dashboard);
            String json = jsonMapper.writeValueAsString(dto);
            FileUtils.write(getFile(dashboard.getId()), json, "UTF-8");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Dashboard readFile(File file) {
        try {
            String json = FileUtils.readFileToString(file, "UTF-8");
            DashboardDto dto = jsonMapper.readValue(json, DashboardDto.class);
            return mapper.map(dto);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
