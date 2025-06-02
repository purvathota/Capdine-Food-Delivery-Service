import React, { useState, useEffect } from "react";
import axios from "axios";
import { Table, TableHead, TableBody, TableRow, TableCell, TablePagination } from "@mui/material";

const DeliveryDrivers = () => {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  
  useEffect(() => {
    fetchData();
  }, []);
  
  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8085/api/drivers/");
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
    <div className="deliverydrivers">
      <Table sx={{ border: '1px solid rgba(224, 224, 224, 1)' }}>
        <TableHead>
          <TableRow>
            <TableCell>Driver_Id</TableCell>
            <TableCell>Driver_name</TableCell>
            <TableCell>Driver_phone</TableCell>
            <TableCell>Driver_vehicle</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {(rowsPerPage > 0
            ? data.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
            : data
          ).map((driver, index) => (
            <TableRow key={index}>
              <TableCell>{driver.driver_id}</TableCell>
              <TableCell>{driver.driver_name}</TableCell>
              <TableCell>{driver.driver_phone}</TableCell>
              <TableCell>{driver.driver_vehicle}</TableCell>
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

export default DeliveryDrivers;

