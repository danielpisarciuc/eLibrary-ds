package com.elibrary.server.dao.impl;

import com.elibrary.server.dao.LibraryBookDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("libraryBookDao")
public class LibraryBookDaoImpl extends AbstractDao implements LibraryBookDao {

}
