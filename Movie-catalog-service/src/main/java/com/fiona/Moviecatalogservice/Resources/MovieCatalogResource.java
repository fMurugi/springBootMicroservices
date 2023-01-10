package com.fiona.Moviecatalogservice.Resources;

import com.fiona.Moviecatalogservice.Models.CatalogItem;
import com.fiona.Moviecatalogservice.Models.Movie;
import com.fiona.Moviecatalogservice.Models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.catalog.Catalog;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")  //this method getCatalog should break if the service is slow
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );
        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://Movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getMovieName(), "test desc", rating.getRating());
        }).collect(Collectors.toList());

    }

    // fallback method is called when the getCatalog method fails or is slow
    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItem("No movie", "no description", 0));
    }
}
