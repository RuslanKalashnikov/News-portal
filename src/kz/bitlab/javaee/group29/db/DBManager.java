package kz.bitlab.javaee.group29.db;

import java.nio.channels.NonWritableChannelException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DBManager {

    private static Connection connection;

    static {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Worldnews", "postgres", "");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Languages> getLanguages(){
        ArrayList<Languages> languages = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM languages ORDER BY id");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                languages.add(new Languages(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")));
            }

            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return languages;


    }
    public static ArrayList<News> getNews() {
        ArrayList<News> news = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT news.id as news_id, news.title,news.short_content, news.content, news.picture_url, news.category_id as category_id, " +
                    "l.name as lang_name, news.post_date, p.name as pub_name, l.id as lang_id, l.code as lang_code, p.id as pub_id, p.description as pub_desc, p.rating as rating, c.name as category_name " +
                    "FROM news " +
                    " INNER JOIN languages l " +
                    "    on l.id = news.language_id " +
                    "INNER JOIN publications p " +
                    "    on news.publication_id = p.id " +
                    "INNER JOIN categories c " +
                    "   on c.id = news.category_id " +
                    "ORDER BY news_id");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getLong("news_id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("post_date"),
                        resultSet.getString("picture_url"),
                        new Languages(
                                resultSet.getLong("lang_id"),
                                resultSet.getString("lang_name"),
                                resultSet.getString("lang_code")
                        ),
                        new Publications(
                                resultSet.getLong("pub_id"),
                                resultSet.getString("pub_name"),
                                resultSet.getString("pub_desc"),
                                resultSet.getDouble("rating")
                        ),
                        new Categories(
                                resultSet.getLong("category_id"),
                                resultSet.getString("category_name")
                        )
                ));



            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return news;


    }

    public static ArrayList<Publications> getPublications() {
        ArrayList<Publications> publications = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM publications ORDER BY rating DESC");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                publications.add(new Publications(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating")
                ));
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return publications;


    }

    public static boolean addNewLang(Languages language){

        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO languages(name, code) " +
                    "VALUES (?, ?)");

            statement.setString(1, language.getName());
            statement.setString(2, language.getCode());


            rows = statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean addNewPub(Publications pub){

        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO publications(name, description, rating) " +
                    "VALUES (?, ?, ?)");

            statement.setString(1, pub.getName());
            statement.setString(2, pub.getDescription());
            statement.setDouble(3, pub.getRating());


            rows = statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean addNewNew(News news){

        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO news(title, short_content, content, post_date, picture_url, language_id, publication_id, category_id) " +
                    "VALUES (?, ?, ?, NOW(), ?, ?, ?,?)");

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getShort_content());
            statement.setString(3, news.getContent());

            statement.setString(4, news.getPicture_url());
            statement.setLong(5, news.getLanguage().getId());
            statement.setLong(6, news.getPublication().getId());
            statement.setLong(7, news.getCategory().getId());


            rows = statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean deleteLang(Long id) {

        int rows = 0;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM languages WHERE id = ?");
            preparedStatement.setLong(1, id);

            rows = preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean deletePub(Long id) {

        int rows = 0;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM publications WHERE id = ?");
            preparedStatement.setLong(1, id);

            rows = preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }


    public static boolean deleteNew(News blog){
        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM comments WHERE blog_id = ?");

            statement.setLong(1, blog.getId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM news WHERE id = ?");

            statement.setLong(1, blog.getId());
            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static Languages getLanguage(Long id){

        Languages language = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM languages WHERE id = ?");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                language = new Languages(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                        );

            }
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return language;

    }


    public static Publications getPublication(Long id){

        Publications publication = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM publications WHERE id = ?");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                publication = new Publications(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating")
                );

            }
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return publication;

    }

    public static News getNew(Long id){

        News news = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT news.id as news_id, news.title,news.short_content, news.content, news.picture_url, news.category_id as category_id, " +
                    "l.name as lang_name, news.post_date, p.name as pub_name, l.id as lang_id, l.code as lang_code, p.id as pub_id, p.description as pub_desc, p.rating as rating, c.name as category_name " +
                    "FROM news " +
                    " INNER JOIN languages l " +
                    "    on l.id = news.language_id " +
                    "INNER JOIN publications p " +
                    "    on news.publication_id = p.id " +
                    "INNER JOIN categories c " +
                    "   on c.id = news.category_id " +
                    "WHERE news.id = ?");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                news = new News(
                        resultSet.getLong("news_id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("post_date"),
                        resultSet.getString("picture_url"),
                        new Languages(
                                resultSet.getLong("lang_id"),
                                resultSet.getString("lang_name"),
                                resultSet.getString("lang_code")
                        ),
                        new Publications(
                                resultSet.getLong("pub_id"),
                                resultSet.getString("pub_name"),
                                resultSet.getString("pub_desc"),
                                resultSet.getDouble("rating")
                        ),
                        new Categories(
                                resultSet.getLong("category_id"),
                                resultSet.getString("category_name")
                        )
                );

            }
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return news;

    }

    public static boolean saveLanguage(Languages lang) {
        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE languages SET name = ?, code = ? " +
                    "WHERE id = ?");

            statement.setString(1, lang.getName());
            statement.setString(2, lang.getCode());
            statement.setLong(3, lang.getId());

            rows = statement.executeUpdate();


        } catch (Exception e){
            e.printStackTrace();
        }

        return rows > 0;

    }

    public static boolean savePub(Publications pub){

        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE publications SET name = ?, description = ?, rating = ? " +
                    " WHERE id = ?");
            statement.setString(1, pub.getName());
            statement.setString(2, pub.getDescription());
            statement.setDouble(3, pub.getRating());
            statement.setLong(4, pub.getId());

            rows = statement.executeUpdate();

            statement.close();


        } catch (Exception e){
            e.printStackTrace();
        }

        return rows > 0;


    }

    public static boolean saveNew(News news){

        int rows = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE news SET title = ?, short_content = ?, content = ?, post_date = NOW(), picture_url = ?, language_id = ?, publication_id = ?, category_id = ?" +
                    " WHERE id = ?");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getShort_content());
            statement.setString(3, news.getContent());
            statement.setString(4, news.getPicture_url());
            statement.setLong(5, news.getLanguage().getId());
            statement.setLong(6, news.getPublication().getId());
            statement.setLong(7, news.getCategory().getId());
            statement.setLong(8, news.getId());


            rows = statement.executeUpdate();

            statement.close();


        } catch (Exception e){
            e.printStackTrace();
        }

        return rows > 0;


    }

    public static AuthUser getUser(String email){

        AuthUser user = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                user = new AuthUser(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name")
                );

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }

    public static boolean saveUser(AuthUser user){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET full_name = ? WHERE id = ?");

            statement.setString(1, user.getFullName());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;

    }

    public static boolean savePassword(AuthUser user){

        int rows = 0;
        boolean exists = false;

        try{

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM password_histories WHERE user_id = ? AND password = ? ");

            statement.setLong(1, user.getId());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                exists = true;
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        if(!exists) {

            try {

                PreparedStatement statement = connection.prepareStatement("" +
                        "UPDATE users SET password = ? WHERE id = ?");

                statement.setString(1, user.getPassword());
                statement.setLong(2, user.getId());

                rows = statement.executeUpdate();
                statement.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try{

                PreparedStatement statement = connection.prepareStatement("" +
                        "INSERT INTO password_histories (user_id, password, added_date) " +
                        "VALUES (?, ?, NOW())");

                statement.setLong(1, user.getId());
                statement.setString(2, user.getPassword());

                statement.executeUpdate();
                statement.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return rows>0;

    }

    public static boolean addUser(AuthUser user){

        int rows = 0;

        Long lastInsertUserId = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name) " +
                    "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());

            rows = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                lastInsertUserId = resultSet.getLong(1);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        if(lastInsertUserId!=null){

            try{

                PreparedStatement statement = connection.prepareStatement("" +
                        "INSERT INTO password_histories (user_id, password, added_date) " +
                        "VALUES (?, ?, NOW())");

                statement.setLong(1, lastInsertUserId);
                statement.setString(2, user.getPassword());

                statement.executeUpdate();
                statement.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return rows>0;

    }

    public static ArrayList<News> getNewsByPub(Long id) {
        ArrayList<News> news = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT news.id as news_id, news.title,news.short_content, news.content, news.picture_url, news.category_id as category_id,  " +
                    "l.name as lang_name, news.post_date, p.name as pub_name, l.id as lang_id, l.code as lang_code, p.id as pub_id, p.description as pub_desc, p.rating as rating, c.name as category_name " +
                    "FROM news " +
                    " INNER JOIN languages l " +
                    "    on l.id = news.language_id " +
                    "INNER JOIN publications p " +
                    "    on news.publication_id = p.id " +
                    "INNER JOIN categories c " +
                    "   on c.id = news.category_id " +
                    "WHERE publication_id = ?");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getLong("news_id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("post_date"),
                        resultSet.getString("picture_url"),
                        new Languages(
                                resultSet.getLong("lang_id"),
                                resultSet.getString("lang_name"),
                                resultSet.getString("lang_code")
                        ),
                        new Publications(
                                resultSet.getLong("pub_id"),
                                resultSet.getString("pub_name"),
                                resultSet.getString("pub_desc"),
                                resultSet.getDouble("rating")
                        ),
                        new Categories(
                                resultSet.getLong("category_id"),
                                resultSet.getString("category_name")
                        )
                ));



            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return news;

    }

    public static ArrayList<Categories> getCategories(){
        ArrayList<Categories> categories = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                categories.add(new Categories(
                        resultSet.getLong("id"),
                        resultSet.getString("name")));
            }

            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return categories;


    }

    public static Categories getCategory(Long id){

        Categories category = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories WHERE id = ?");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                category = new Categories(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );

            }
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return category;

    }


    public static ArrayList<News> getNewsByPubByCategoty(Long pub_id, Long category_id) {
        ArrayList<News> news = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT news.id as news_id, news.title,news.short_content, news.content, news.picture_url, news.category_id as category_id,  " +
                    "l.name as lang_name, news.post_date, p.name as pub_name, l.id as lang_id, l.code as lang_code, p.id as pub_id, p.description as pub_desc, p.rating as rating, c.name as category_name " +
                    "FROM news " +
                    " INNER JOIN languages l " +
                    "    on l.id = news.language_id " +
                    "INNER JOIN publications p " +
                    "    on news.publication_id = p.id " +
                    "INNER JOIN categories c " +
                    "   on c.id = news.category_id " +
                    "WHERE publication_id = ? AND category_id =?");

            statement.setLong(1, pub_id);
            statement.setLong(2, category_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(
                        resultSet.getLong("news_id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("post_date"),
                        resultSet.getString("picture_url"),
                        new Languages(
                                resultSet.getLong("lang_id"),
                                resultSet.getString("lang_name"),
                                resultSet.getString("lang_code")
                        ),
                        new Publications(
                                resultSet.getLong("pub_id"),
                                resultSet.getString("pub_name"),
                                resultSet.getString("pub_desc"),
                                resultSet.getDouble("rating")
                        ),
                        new Categories(
                                resultSet.getLong("category_id"),
                                resultSet.getString("category_name")
                        )
                ));



            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return news;

    }

    public static boolean addComment(Comments comment){

        int rows = 0;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (user_id, blog_id, comment, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setLong(1, comment.getUser().getId());
            statement.setLong(2, comment.getBlog().getId());
            statement.setString(3, comment.getComment());

            rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Comments> getCommentsByBlogId(Long blogId){

        ArrayList<Comments> comments = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.post_date, c.comment, c.user_id, c.blog_id, u.full_name " +
                    "FROM comments c " +
                    "INNER JOIN users u on c.user_id = u.id " +
                    "WHERE c.blog_id = ? " +
                    "ORDER BY c.post_date DESC ");

            statement.setLong(1, blogId);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                comments.add(
                        new Comments(
                                resultSet.getLong("id"),
                                new AuthUser(
                                        resultSet.getLong("user_id"),
                                        null, null,
                                        resultSet.getString("full_name")
                                ),
                                new News(resultSet.getLong("blog_id")),
                                resultSet.getString("comment"),
                                resultSet.getTimestamp("post_date")
                        )
                );

                statement.close();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return comments;

    }

    public static HashMap<String, String> likeBlog(News blog, AuthUser user){

        int likes = 0;
        boolean liked = false;

        liked = checkLike(blog, user);

        try{

            PreparedStatement statement;
            if(liked){
                statement = connection.prepareStatement("DELETE FROM likes WHERE blog_id = ? AND user_id = ?");
            }else{
                statement = connection.prepareStatement("INSERT INTO likes (blog_id, user_id) VALUES (?, ?)");
            }

            statement.setLong(1, blog.getId());
            statement.setLong(2, user.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT COUNT(*) AS like_amount FROM likes WHERE blog_id = ? ");

            statement.setLong(1, blog.getId());

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                likes = resultSet.getInt("like_amount");
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news SET like_amount = ? WHERE id = ?");

            statement.setInt(1, likes);
            statement.setLong(2, blog.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("liked", !liked+"");
        map.put("likes", likes+"");

        return map;

    }

    public static boolean checkLike(News blog, AuthUser user){

        boolean liked = false;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM likes WHERE blog_id = ? AND user_id = ? ");
            statement.setLong(1, blog.getId());
            statement.setLong(2, user.getId());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                liked = true;
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return liked;

    }

}



