package unq.edu.li.pdes.unqpremium.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryBuilder<F> {
	
	EntityManager entityManager;
	Map<String, Object> parameters = new HashMap<String, Object>();
	StringBuilder queryBuilder = new StringBuilder();
	F filter = null;
	String alias = null;
	
	static Logger LOGGER = LoggerFactory.getLogger(QueryBuilder.class);

	public QueryBuilder(EntityManager entityManager, F filter) {
		super();
		this.entityManager = entityManager;
		this.filter = filter;
	}
	
	public QueryBuilder<F> add(Predicate<F> predicate,  String statement) {
		if (!predicate.test(filter)) {
			return this;	
		}
	    queryBuilder.append(statement);
		return this;
	}
	
	public QueryBuilder<F> add(Predicate<F> predicate,  String statement, String parameterName, Supplier<Object> parameter) {
		if (!predicate.test(filter)) {
			return this;	
		}
	    queryBuilder.append(statement);
	    parameters.put(parameterName, parameter.get());
		return this;
	}
	
	
	public QueryBuilder<F> add(Predicate<F> predicate,  Consumer<F> consumer) {
		if (!predicate.test(filter)) {
			return this;	
		}
		consumer.accept(filter);
		return this;
	}

	public QueryBuilder<F> append(String statement) {
	    queryBuilder.append(statement);
		return this;
	}
	
	public QueryBuilder<F> params(String parameterName, Object parameter) {
	    parameters.put(parameterName, parameter);
		return this;
	}
	
	public <R> TypedQuery<R> createQuery(Class<R> resultClass) {
		LOGGER.debug("Query: " + queryBuilder.toString());
		TypedQuery<R> query = entityManager.createQuery(queryBuilder.toString(), resultClass);
		Set<Map.Entry<String, Object>> set =  parameters.entrySet();
		for (Map.Entry<String, Object> entry : set) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		LOGGER.debug("parameters "  + parameters);
		return query;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getAlias() {
		return alias;
	}
	
	@Override
	public String toString() {
		return queryBuilder.toString();
	}
}
