package vn.midatri.mapper;


import org.springframework.stereotype.Component;
import vn.midatri.dto.tableTop.TableTopRegister;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;

@Component
public class TableTopMapper {
    public TableTopResult toDTO(TableTop tableTop) {
        return new TableTopResult()
                .setId(tableTop.getId())
                .setTitle(tableTop.getTitle())
                .setStatus(tableTop.getStatus())
                .setCapacity(tableTop.getCapacity())
                .setCreateAt(tableTop.getCreateAt())
                .setUpdateAt(tableTop.getUpdateAt())
                .setContent(tableTop.getContent());
    }

    public TableTop toModelRegister(TableTopRegister tableTopRegister) {
        return new TableTop()
                .setId(tableTopRegister.getId())
                .setStatus(tableTopRegister.getStatus())
                .setCapacity(tableTopRegister.getCapacity())
                .setCreateAt(tableTopRegister.getCreateAt())
                .setTitle(tableTopRegister.getTitle())
                .setContent(tableTopRegister.getContent());
    }

    public TableTop toModelResult(TableTopResult tableTopResult) {
        return new TableTop()
                .setId(tableTopResult.getId())
                .setStatus(tableTopResult.getStatus())
                .setCreateAt(tableTopResult.getCreateAt())
                .setUpdateAt(tableTopResult.getUpdateAt())
                .setCapacity(tableTopResult.getCapacity())
                .setTitle(tableTopResult.getTitle())
                .setContent(tableTopResult.getContent());

    }
}
