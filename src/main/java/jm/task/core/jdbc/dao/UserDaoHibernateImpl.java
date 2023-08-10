package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public void exeMethod(String qwery){
        try ( Session session = Util.getSessionFactory().openSession();){
            session.beginTransaction();
            session.createSQLQuery(qwery).executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String qwery = """
                CREATE TABLE Users (
                  `id` INT NOT NULL AUTO_INCREMENT,
                  `name` VARCHAR(45) NOT NULL,
                  `lastName` VARCHAR(45) NOT NULL,
                  `age` INT NOT NULL,
                  PRIMARY KEY (`id`),
                  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);""";

        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.createSQLQuery(qwery).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Таблица уже существует");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String qwery = "DROP TABLE  Users;";
        try ( Session session = Util.getSessionFactory().openSession();){
            session.beginTransaction();
            session.createSQLQuery(qwery).executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println("Не получилось удалить таблицу");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try ( Session session = Util.getSessionFactory().openSession();){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
       String qwery = "delete User where id = id";
        try ( Session session = Util.getSessionFactory().openSession();){
            session.beginTransaction();
            session.createQuery(qwery).executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try ( Session session = Util.getSessionFactory().openSession();){
            session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        userList.stream().forEach(System.out::println);
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String qwery = "delete User";
        try ( Session session = Util.getSessionFactory().openSession();){
            session.beginTransaction();
            session.createQuery(qwery).executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
