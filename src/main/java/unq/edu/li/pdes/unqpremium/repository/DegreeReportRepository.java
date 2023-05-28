package unq.edu.li.pdes.unqpremium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import unq.edu.li.pdes.unqpremium.dto.DegreeFilterDTO;
import unq.edu.li.pdes.unqpremium.model.Degree;
import unq.edu.li.pdes.unqpremium.model.Subject;

@Component
public class DegreeReportRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Degree> search(DegreeFilterDTO degreeFilter) {
		QueryBuilder<DegreeFilterDTO> queryBuilder = new QueryBuilder<>(entityManager, degreeFilter);
		queryBuilder.append("SELECT degree FROM Degree degree "
                + "WHERE 1=1 ");
		TypedQuery<Degree> query = createQueryForSearch(degreeFilter, queryBuilder, Degree.class, Boolean.FALSE);
		List<Degree> degreesList = query.getResultList();
		return degreesList;
	}
	
	
	public List<Subject> searchSubjectByFilter(DegreeFilterDTO degreeFilter) {
		QueryBuilder<DegreeFilterDTO> queryBuilder = new QueryBuilder<>(entityManager, degreeFilter);
		queryBuilder.append("SELECT dsubjects FROM Degree degree "
				+ "join degree.subjects dsubjects "
                + "WHERE 1=1 ");
		TypedQuery<Subject> query = createQueryForSearch(degreeFilter, queryBuilder, Subject.class, Boolean.TRUE);
		List<Subject> degreesList = query.getResultList();
		return degreesList;
	}
	
	private <T> TypedQuery<T> createQueryForSearch(DegreeFilterDTO degreeFilter, QueryBuilder<DegreeFilterDTO> queryBuilder, Class<T> classFilter, boolean isSubjectSerch) {
		if(isSubjectSerch) {
			queryBuilder.add(filter -> StringUtils.isNotBlank(filter.getLikeName()), " AND (UPPER(dsubjects.name) LIKE UPPER(:likeName)) ", "likeName", ()-> "%" + degreeFilter.getLikeName() + "%");
		}else {
			queryBuilder.add(filter -> StringUtils.isNotBlank(filter.getLikeName()), " AND (UPPER(degree.name) LIKE UPPER(:likeName)) ", "likeName", ()-> "%" + degreeFilter.getLikeName() + "%");
		}
		queryBuilder.add(filter -> !filter.getDegreeIds().isEmpty(), " AND degree.id IN (:degreeIds) ", "degreeIds", ()-> degreeFilter.getDegreeIds());
		TypedQuery<T> query =  queryBuilder.createQuery(classFilter);
		return query; 
	}
}
