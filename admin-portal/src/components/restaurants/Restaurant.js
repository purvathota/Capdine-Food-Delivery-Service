import React, { useState, useEffect } from "react";
import axios from "axios";
import { Table, TableHead, TableBody, TableRow, TableCell, TablePagination } from "@mui/material";

const Restaurant = () => {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  
  useEffect(() => {
    fetchData();
  }, []);
  
  const fetchData = async () => {
    try {
      const response = await axios.get(`http://localhost:8085/api/restaurants/`);
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
    <div className="restaurant">
      <Table sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
        <TableHead>
          <TableRow>
            <TableCell>Restaurant_Id</TableCell>
            <TableCell>Restaurant_Address</TableCell>
            <TableCell>Restaurant_Name</TableCell>
            <TableCell>Restaurant_Phone</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {(rowsPerPage > 0
            ? data.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
            : data
          ).map((restaurant, index) => (
            <TableRow key={index}>
              {console.log(restaurant)}
              <TableCell>{restaurant.restaurant_id}</TableCell>
              <TableCell>{restaurant.restaurant_address}</TableCell>
              <TableCell>{restaurant.restaurant_name}</TableCell>
              <TableCell>{restaurant.restaurant_phone}</TableCell>
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

export default Restaurant;


