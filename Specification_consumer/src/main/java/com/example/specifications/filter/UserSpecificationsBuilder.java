package com.example.specifications.filter;

import com.example.specifications.model.entity.UserCustom;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserSpecificationsBuilder {

    private final List<SpecSearchCriteria> params = new ArrayList<>();

    public UserSpecificationsBuilder with(
            String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        SpecSearchCriteria specSearchCriteria = null;
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");
                boolean or = operation.toUpperCase(Locale.ROOT).equals("OR");
                boolean and = operation.toUpperCase(Locale.ROOT).equals("AND");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
                specSearchCriteria = new SpecSearchCriteria(key, op, value);
                if (or) {
                    specSearchCriteria.setOrPredicate(true);
                } else if (and) {
                    specSearchCriteria.setAndPredicate(true);
                }
            }

            params.add(specSearchCriteria);
        }
        return this;
    }

    public Specification<UserCustom> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<UserCustom> result = new UserSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new UserSpecification(params.get(i)))
                    : Specification.where(result).and(new UserSpecification(params.get(i)));
        }

        return result;
    }
}
