package vn.midatri.mapper;


import org.springframework.stereotype.Component;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;

@Component
public class TableTopMapper {
    public TableTopResult toDTO(TableTop tableTop){
        TableTopResult tableTopResult = new TableTopResult();
        tableTopResult.setId(tableTop.getId());
        tableTopResult.setCode(tableTop.getCode());
        tableTopResult.setStatus(tableTop.getStatus());
        tableTopResult.setCapacity(tableTop.getCapacity());
        tableTopResult.setContent(tableTop.getContent());
        tableTopResult.setDeleted(tableTop.getDeleted());
        return tableTopResult;
    }
}
