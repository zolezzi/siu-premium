package unq.edu.li.pdes.unqpremium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import unq.edu.li.pdes.unqpremium.dto.SemesterDegreeSubjectFilterDTO;
import unq.edu.li.pdes.unqpremium.model.SemesterDegreeSubject;

@Component
public class SemesterDegreeSubjectReportRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<SemesterDegreeSubject> search(SemesterDegreeSubjectFilterDTO semesterDegreeSubjectFilter) {
		QueryBuilder<SemesterDegreeSubjectFilterDTO> queryBuilder = new QueryBuilder<>(entityManager, semesterDegreeSubjectFilter);
		queryBuilder.append("SELECT sds FROM SemesterDegreeSubject sds "
                + "WHERE 1=1 ");
		TypedQuery<SemesterDegreeSubject> query = createQueryForSearch(semesterDegreeSubjectFilter, queryBuilder, SemesterDegreeSubject.class, false);
		List<SemesterDegreeSubject> resultList = query.getResultList();
		return resultList;
	}
	
	private <T> TypedQuery<T> createQueryForSearch(SemesterDegreeSubjectFilterDTO semesterDegreeSubjectFilter, QueryBuilder<SemesterDegreeSubjectFilterDTO> queryBuilder, Class<T> classFilter, boolean count) {
		queryBuilder.add(filter -> StringUtils.isNotBlank(filter.getLikeName()), " AND (UPPER(sds.degree.name) LIKE (:likeName) OR UPPER(sds.subject.name) LIKE UPPER(:likeName)) ", "likeName", ()-> "%" + semesterDegreeSubjectFilter.getLikeName() + "%");
		queryBuilder.add(filter -> filter.getSemesterId() != null, " AND sds.semester.id IN (:semesterId) ", "semesterId", ()-> semesterDegreeSubjectFilter.getSemesterId());
		queryBuilder.add(filter -> !filter.getDegreeIds().isEmpty(), " AND sds.degree.id IN (:degreeIds) ", "degreeIds", ()-> semesterDegreeSubjectFilter.getDegreeIds());
		queryBuilder.add(filter -> !filter.getSubjectIds().isEmpty(), " AND sds.degree.id IN (:subjectIds) ", "subjectIds", ()-> semesterDegreeSubjectFilter.getSubjectIds());
		TypedQuery<T> query =  queryBuilder.createQuery(classFilter);
		return query; 
	}
}
