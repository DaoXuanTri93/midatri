package vn.midatri.mapper;


import org.springframework.stereotype.Component;
import vn.midatri.dto.tableTop.TableTopRegister;
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
    public TableTop toModelRegister(TableTopRegister tableTopRegister){
        return new TableTop()
                .setId(tableTopRegister.getId())
                .setCode(tableTopRegister.getCode())
                .setStatus(tableTopRegister.getStatus())
                .setCapacity(tableTopRegister.getCapacity())
                .setContent(tableTopRegister.getContent());
    }

    public  TableTop toModelResult (TableTopResult tableTopResult){
        return  new TableTop()
                .setId(tableTopResult.getId())
                .setCode(tableTopResult.getCode())
                .setStatus(tableTopResult.getStatus())
                .setCapacity(tableTopResult.getCapacity())
                .setContent(tableTopResult.getContent());

    }
}
