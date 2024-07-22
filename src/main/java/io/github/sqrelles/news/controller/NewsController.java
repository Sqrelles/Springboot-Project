package io.github.sqrelles.news.controller;

import io.github.sqrelles.news.model.News;
import io.github.sqrelles.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NewsController {


    private NewsService newsService;

    @GetMapping("/getall")
    public ResponseEntity<List<News>> getNews() {

        return new ResponseEntity<>(newsService.getAllNews(), HttpStatus.OK);
    }
    @PostMapping("/news/create")
    public ResponseEntity<News> createNews(@RequestBody News news) {
        return new ResponseEntity<>(newsService.createNews(news), HttpStatus.CREATED);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable int id) {

        return new ResponseEntity<>(newsService.getNewsById(id), HttpStatus.OK);
    }

    @DeleteMapping("/news/delete/{id}")
    public ResponseEntity<News> deleteNewsById(@PathVariable int id) {
        newsService.deleteNewsById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<News> updateNewsById(@PathVariable int id, @RequestBody News news) {
        newsService.updateNews(id, news);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/news")
    public List<News> getNewsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date)
    {
        return newsService.getNewsByDate(date);
    }


    @GetMapping("/news/dates")
    public List<News> getNewsArticlesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return newsService.getNewsBetweenDates(startDate, endDate);
    }

    @PostMapping("/news/image")
    public ResponseEntity<News> createNewsWithImage(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("image") MultipartFile image) throws IOException {
        News news = newsService.createNewsWithImg(title, content, author, image);
        return new ResponseEntity<>(news, HttpStatus.CREATED);
    }

}
