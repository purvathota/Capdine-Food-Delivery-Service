import React, { useState, useEffect } from "react";
import axios from "axios";
import { Table, TableHead, TableBody, TableRow, TableCell, TablePagination } from "@mui/material";

const Datatable = () => {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  
  useEffect(() => {
    fetchData();
  }, []);
  
  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8085/api/customers/");
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
    <div className="datatable">
      <Table sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
        <TableHead>
          <TableRow>
            <TableCell>Customer_Id</TableCell>
            <TableCell>Customer_name</TableCell>
            <TableCell>Customer_email</TableCell>
            <TableCell>Customer_phone</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {(rowsPerPage > 0
            ? data.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
            : data
          ).map((customer, index) => (
            <TableRow key={index}>
     
              <TableCell>{customer.customer_id}</TableCell>
              <TableCell>{customer.customer_name}</TableCell>
              <TableCell>{customer.customer_email}</TableCell>
              <TableCell>{customer.customer_phone}</TableCell>
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

export default Datatable;

