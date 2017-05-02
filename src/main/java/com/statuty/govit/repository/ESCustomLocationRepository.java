package com.statuty.govit.repository;

import com.statuty.govit.domain.Location;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Repository
public class ESCustomLocationRepository {
    @Autowired
    private final ElasticsearchTemplate elasticsearchTemplate;

    public ESCustomLocationRepository(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public List<Location> find(double latitude, double longitude, String distance, String name, String category, String workingDay, String workingTime, int page, int size) {
        BoolQueryBuilder queryBuilder = boolQuery()
            .filter(geoDistanceQuery("coordinates").lat(latitude).lon(longitude).distance(distance))
            .must(Objects.nonNull(name) ? matchQuery("name", name) : matchAllQuery())
            .must(Objects.nonNull(category) ? matchQuery("category.name", category) : matchAllQuery())
            .must(Objects.nonNull(workingDay) ? nestedQuery("workingDays",
                boolQuery()
                    .must(matchQuery("workingDays.day", workingDay))
                    .must(Objects.nonNull(workingTime) ? nestedQuery("workingDays.time",
                        boolQuery()
                            .must(rangeQuery("workingDays.time.from").lte(workingTime))
                            .must(rangeQuery("workingDays.time.to").gte(workingTime))
                    ) : matchAllQuery())
            ) : matchAllQuery());
        SearchQuery query = new NativeSearchQueryBuilder()
            .withQuery(queryBuilder)
            .withPageable(new PageRequest(page, size))
            .build();
        return elasticsearchTemplate.queryForPage(query, Location.class).getContent();
    }

    public List<String> findCategories() {
        SearchQuery query = new NativeSearchQueryBuilder()
            .withQuery(matchAllQuery())
            .addAggregation(AggregationBuilders.terms("categories").field("category.name"))
            .build();
        return elasticsearchTemplate.query(query,
            response -> ((StringTerms) response
                .getAggregations().asMap().get("categories"))
                .getBuckets()
                .stream()
                .map(bucket -> bucket.getKey().toString())
                .collect(Collectors.toList()));
    }
}
