package vn.midatri.mapper;


import org.springframework.stereotype.Component;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;

@Component
public class TableTopMapper {
    public TableTopResult toDTO(TableTop tableTop){
        return  new TableTopResult()
        .setId(tableTop.getId())
        .setCode(tableTop.getCode())
        .setStatus(tableTop.getStatus())
        .setCapacity(tableTop.getCapacity())
        .setContent(tableTop.getContent())
        .setDeleted(tableTop.getDeleted());
    }
}
