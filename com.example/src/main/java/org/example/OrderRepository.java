package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final String SAVE_ORDER_QUERY = "INSERT INTO \"order\" (product_id, customer_id, qty) VALUES (? , ? , ?)";
    private static final String SELECT_LAST_ORDERS = "SELECT * FROM \"order\" ORDER BY id DESC LIMIT ?";
    private static final String SELECT_LAST_ORDERS_24H = "SELECT * FROM \"order\" WHERE placed_at > ? ORDER BY placed_at DESC";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM \"order\" WHERE id=?";
    private static final String DELETE_ORDER_BY_NUMBER = "DELETE * FROM \"order\" WHERE id=?";
    private Connection connection;

    public OrderRepository setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    public OrderTO loadById (int id) throws SQLException{
        PreparedStatement statement=connection.prepareStatement((SELECT_ORDER_BY_ID));
        statement.setInt(1, id);
        ResultSet resultSet=statement.executeQuery();

        resultSet.next();
        return toOrderTO(resultSet);
    }

    int save(OrderTO orderTO) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SAVE_ORDER_QUERY);
        statement.setInt(1, orderTO.getProductId());
        statement.setInt(2, orderTO.getClientId());
        statement.setInt(3, orderTO.getQty());
//		if (orderTO.getEmployeeId() != null) {
//			statement.setInt(4, orderTO.getEmployeeId());
//		}
        return statement.executeUpdate();
    }

    public List<OrderTO> loadLastOrders(int limit) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_LAST_ORDERS);
        statement.setInt(1, limit);
        ResultSet resultSet = statement.executeQuery();
        List<OrderTO> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(toOrderTO(resultSet));
        }
        return result;
    }

    public List<OrderTO> loadLast24hOrders() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_LAST_ORDERS_24H);
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        statement.setTimestamp(1, Timestamp.from(localDateTime.toInstant(ZoneOffset.UTC)));
        ResultSet resultSet = statement.executeQuery();
        List<OrderTO> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(toOrderTO(resultSet));
        }
        return result;
    }

    private OrderTO toOrderTO(ResultSet resultSet) throws SQLException {
        OrderTO orderTO = new OrderTO();
        orderTO.setProductId(resultSet.getInt("product_id"));
        orderTO.setClientId(resultSet.getInt("customer_id"));
        orderTO.setQty(resultSet.getInt("qty"));
        orderTO.setPlacedAt(resultSet.getTimestamp("placed_at").toLocalDateTime());
        return orderTO;
    }

    public boolean deleteOrder(int numberAsInt) throws SQLException {
        PreparedStatement statement=connection.prepareStatement((SELECT_ORDER_BY_ID));
//        statement.setInt(1, id);
        ResultSet resultSet=statement.executeQuery();
        resultSet.next();
return true;
    }
}

