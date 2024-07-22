package io.github.sqrelles.news.service;

import io.github.sqrelles.news.model.News;
import io.github.sqrelles.news.repository.NewsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Service
@AllArgsConstructor
public class NewsService {
    private final NewsRepo newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public News getNewsById(int id) {
        News news = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
        return news;
    }

    public void deleteNewsById(int id) {
        newsRepository.deleteById(id);
    }

    public void updateNews(int id, News news) {
      News news1 = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
      news1.setTitle(news.getTitle());
      news1.setContent(news.getContent());
      news1.setAuthor(news.getAuthor());
      news1.setDate(news.getDate());
      newsRepository.save(news1);
    }

    public List<News> getNewsByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return newsRepository.findAllByDate(year, month ,day);
    }

    public List<News> getNewsBetweenDates(Date start, Date end) {
        return newsRepository.findAllBetweenDates(start, end);
    }

    public News createNewsWithImg(String title, String content, String author, MultipartFile image) throws IOException {
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setAuthor(author);
        String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
        news.setImage(base64Image);
        return newsRepository.save(news);
    }



   /* @Bean
    public CommandLineRunner run(NewsRepo newsRepository) {
        return args -> {
            newsRepository.alterImageColumn();
        };
    }*/
}

