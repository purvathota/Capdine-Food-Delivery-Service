import React, { useState, useEffect } from "react";
import axios from "axios";
import { Table, TableHead, TableBody, TableRow, TableCell, TablePagination } from "@mui/material";

const Orders = () => {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  
  useEffect(() => {
    fetchData();
  }, []);
  
  const fetchData = async (order_id) => {
    try {
      const response = await axios.get(`http://localhost:8085/api/orders/`);
      setData(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <div className="orders">
      <Table sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
        
        <TableHead>
          <TableRow>
            <TableCell>Order_Id</TableCell>
            <TableCell>Customer_Id</TableCell>
            <TableCell>Delivery_Driver_Id</TableCell>
            <TableCell>Order_Date</TableCell>
            <TableCell>Order_Status</TableCell>
            <TableCell>Restaurant_Id</TableCell>
            
          </TableRow>
        </TableHead>
        <TableBody>
          {(rowsPerPage > 0
            ? data.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
            : data
          ).map((order, index) => (
            <TableRow key={index}>
              <TableCell>{order.order_id}</TableCell>
              <TableCell>{order.customer_id}</TableCell>
              <TableCell>{order.delivery_driver_id}</TableCell>
              <TableCell>{order.order_date}</TableCell>
              <TableCell>{order.order_status}</TableCell>
              <TableCell>{order.restaurant_id}</TableCell>
              
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <TablePagination
        rowsPerPageOptions={[5, 10, 25]} 
        component="div"
        count={data.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </div>
  );
};

export default Orders;

