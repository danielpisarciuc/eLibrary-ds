package com.elibrary.server.dao.impl;

import com.elibrary.server.dao.ReferenceDataDao;
import com.elibrary.server.dao.entity.ReferenceDataEntity;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("referenceDataDao")
public class ReferenceDataDaoImpl extends AbstractDao implements ReferenceDataDao {
    private final Logger LOGGER = Logger.getLogger(ReferenceDataDaoImpl.class);

    @Override
    public List retrieveReferenceData(String type) throws LibraryException {
        Criteria criteria = getSession().createCriteria(ReferenceDataEntity.class);
        criteria.add(Restrictions.eq("type", type));

        List result = criteria.list();
        if (result.isEmpty()) {
            LOGGER.error(LibraryMessage.NO_REFERENCE_DATA.getMessage() + "->" + type);
            throw new LibraryException(LibraryMessage.NO_REFERENCE_DATA.getMessage());
        }

        return result;
    }
}
