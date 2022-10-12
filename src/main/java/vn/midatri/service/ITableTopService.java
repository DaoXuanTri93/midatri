package vn.midatri.service;

import vn.midatri.repository.model.TableTop;

import java.util.List;

public interface ITableTopService {
    List<TableTop> findAllByDeleted(Boolean status);
}
