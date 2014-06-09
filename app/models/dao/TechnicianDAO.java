package models.dao;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.ContactInformation;
import models.Location;
import models.technician.Schedule;
import models.technician.Technician;
import models.technician.WorkingHours;

import org.joda.time.DateTime;

import com.google.gson.reflect.TypeToken;

import play.Logger;
//import utility.GsonUtil;

/**
 * DAO Pattern to CRUD sql database
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 * 
 */
public class TechnicianDAO {
    Connection connection = null;
    PreparedStatement st = null;
    ResultSet resultSet = null;

    public TechnicianDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    /**
     * Create new entry
     * @param technician
     */
    public void add(Technician technician) {
        try {
            String queryString = "INSERT INTO Technicians(name, title, description, "
                    + "image, createdOn, lastUpdated, isExternal, contact, schedule, workingHours) VALUES(?,?,?,?)";
            connection = getConnection();
            st = connection.prepareStatement(queryString);
            st.setString(1,
                    technician.firstName + " " + technician.lastName);
            st.setString(2, technician.title);
            st.setString(3, technician.description);
            st.setString(4, technician.image);
            st.setDate(5, new Date(new DateTime().getMillis()));
            st.setDate(6, new Date(new DateTime().getMillis()));
            st.setBoolean(7, technician.isExternal);
//            st.setString(8,
//                    GsonUtil.createJson(technician.contactInformation));
            //st.setString(9, GsonUtil.createJson(technician.getSchedule()));
//            st.setString(10, GsonUtil.createJson(technician.workingHours));
            st.executeUpdate();
           Logger.info("Technician Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Update date
     * @param technician
     */
    public void update(Technician technician) {

        try {
            String queryString = "UPDATE student SET Name=? WHERE id=?";
            connection = getConnection();
            st = connection.prepareStatement(queryString);
            st.setString(1,
                    technician.firstName + " " + technician.lastName);
            st.setLong(2, technician.getId());
            st.executeUpdate();
            System.out.println("Technician table updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            }

            catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    /**
     * Delete entry
     * @param id
     */
    public void delete(long id) {

        try {
            String queryString = "DELETE FROM technician WHERE id=?";
            connection = getConnection();
            st = connection.prepareStatement(queryString);
            st.setLong(1, id);
            st.executeUpdate();
            System.out.println("tech deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Retrieves technicians who are assocciated with specified location
     * @return
     */
    public List<Technician> getTechniciansByLocation(Location location) {
        try {
            String queryString = "SELECT * FROM student";
            connection = getConnection();
            st = connection.prepareStatement(queryString);
            resultSet = st.executeQuery();
            List<Technician> techs = new ArrayList<Technician>();
            
            Type listType = new TypeToken<List<WorkingHours>>() {
            }.getType();
            
            while (resultSet.next()) {
                // TODO check whether he/she can go to the specified location
                System.out.println("Tech " + resultSet.getLong("id")
                        + ", Name " + resultSet.getString("name"));
                /*
                Technician t = new Technician(resultSet.getString("name"), "",
                        GsonUtil.fromJsonToObj(resultSet.getString("contact"),
                                ContactInformation.class),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        GsonUtil.fromJsonToWHList(
                                resultSet.getString("workingHours"),
                                listType),
                        resultSet.getString("image"),
                        GsonUtil.fromJsonToObj(resultSet.getString("schedule"),
                                Schedule.class),
                        resultSet.getBoolean("isExternal"), null);*/
              //  t.setID(resultSet.getInt("id"));
               // techs.add(t);
            }
            return techs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }
    /**firstName+" "+lastname
     * Retrieves technicians by name
     * @return
     */
    public List<Technician> getTechniciansByName(String name) {
        try {
            String queryString = "SELECT * FROM student WHERE name=?";
            connection = getConnection();
            st = connection.prepareStatement(queryString);
            st.setString(1, "name");
            resultSet = st.executeQuery();
            List<Technician> techs = new ArrayList<Technician>();
            while (resultSet.next()) {
                // TODO check whether he/she can go to the specified location
                System.out.println("Tech " + resultSet.getLong("id")
                        + ", Name " + resultSet.getString("name"));
              /*  Technician t = new Technician(resultSet.getString("name"), "",
                        GsonUtil.fromJsonToObj(resultSet.getString("contact"),
                                ContactInformation.class),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        GsonUtil.fromJsonToWHList(
                                resultSet.getString("workingHours"),
                                WorkingHours.class),
                        resultSet.getString("image"),
                        GsonUtil.fromJsonToObj(resultSet.getString("schedule"),
                                Schedule.class),
                        resultSet.getBoolean("isExternal"), null);
              //  t.setID(resultSet.getInt("id"));
                techs.add(t);*/
            }
            return techs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * Retrieves all technicians
     * @return
     */
    public List<Technician> getAllTechnicians() {
        try {
            String queryString = "SELECT * FROM student";
            connection = getConnection();
            st = connection.prepareStatement(queryString);
            resultSet = st.executeQuery();
            List<Technician> techs = new ArrayList<Technician>();
            while (resultSet.next()) {

                System.out.println("Tech " + resultSet.getLong("id")
                        + ", Name " + resultSet.getString("name"));
               /* Technician t = new Technician(resultSet.getString("name"), "",
                        GsonUtil.fromJsonToObj(resultSet.getString("contact"),
                                ContactInformation.class),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        GsonUtil.fromJsonToWHList(
                                resultSet.getString("workingHours"),
                                WorkingHours.class),
                        resultSet.getString("image"),
                        GsonUtil.fromJsonToObj(resultSet.getString("schedule"),
                                Schedule.class),
                        resultSet.getBoolean("isExternal"), null);
            //    t.setID(resultSet.getInt("id"));
                techs.add(t);*/
            }
            return techs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (st != null)
                    st.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }
    
}
