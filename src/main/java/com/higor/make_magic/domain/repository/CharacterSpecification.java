package com.higor.make_magic.domain.repository;

import com.higor.make_magic.domain.entity.Character;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CharacterSpecification implements Specification<Character> {

    private final Character filter;

    public CharacterSpecification(Character filter) {
        super();
        this.filter = filter;
    }

    /**
     * This method create a filter to database query with the query strings parameters sent in request
     */
    @Override
    public Predicate toPredicate(Root<Character> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if(this.filter.getHouse() != null && !this.filter.getHouse().isEmpty()){
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("house"), this.filter.getHouse()));
        }

        return predicate;
    }
}
