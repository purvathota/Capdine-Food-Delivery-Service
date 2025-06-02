import React, { useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
 
const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-image: url('https://cdn.dribbble.com/users/3929140/screenshots/13480628/ezgif.com-optimize__1_.gif');
  background-size: cover;
  background-position: center;
`;
 
const FormContainer = styled.div`
  width: 100%;
  max-width: 400px;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-left: 550px;
`;
 
const PrimaryButton = styled.button`
  background-color: #fe9e0d;
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  border-radius: 4px;
  margin-top: 10px;
`;
 
const FormField = styled.div`
  margin-bottom: 15px;
`;
 
const FormLabel = styled.label`
  display: block;
  margin-bottom: 5px;
`;
 
const FormInput = styled.input`
  padding: 8px;
  width: 100%;
  box-sizing: border-box;
`;
 
function DriverLayout() {
  const [deliveryDriverId, setDeliveryDriverId] = useState('');
  const [orderId, setOrderId] = useState('');
 
  const updateOrderStatus = () => {
    const cleanOrderId = orderId.trim();  // Remove whitespace
    return axios.put(`http://localhost:8085/api/orders/${cleanOrderId}/status`, { status: 'Delivered' });
  };
  const updateDriverAfterDelivery = () => {
    const cleanOrderId = orderId.trim();  // Ensure no leading/trailing spaces
    const cleanDriverId = deliveryDriverId.trim();  // Also clean the driver ID
    return axios.put(`http://localhost:8085/api/drivers/updateDriverAfterDeliver/${cleanOrderId}/${cleanDriverId}`);
  };
 
  const handleDelivery = () => {
    if (!orderId.trim() || !deliveryDriverId.trim()) {
      console.error("Invalid Order ID or Driver ID");
      return; // Prevent API call
    }
    updateOrderStatus()
      .then(response => {
        console.log('Order status updated successfully:', response.data);
        window.alert(`${orderId} has been successfully delivered`);
        return updateDriverAfterDelivery();  // Chain the promise to execute after order status update
      })
      .then(response => {
        console.log('Driver updated successfully:', response.data);
      })
      .catch(error => {
        console.error('Error:', error.response ? error.response.data : error.message);
      });
  };
 
  return (
<Container>
<FormContainer>
<h2>Driver Dashboard</h2>
<FormField>
<FormLabel htmlFor="deliveryDriverId">Delivery Driver ID:</FormLabel>
<FormInput
            type="text"
            id="deliveryDriverId"
            value={deliveryDriverId}
            onChange={e => setDeliveryDriverId(e.target.value)}
          />
</FormField>
<FormField>
<FormLabel htmlFor="orderId">Order ID:</FormLabel>
<FormInput
            type="text"
            id="orderId"
            value={orderId}
            onChange={e => setOrderId(e.target.value)}
          />
</FormField>
<PrimaryButton type="button" onClick={handleDelivery}>
          Delivered
</PrimaryButton>
</FormContainer>
</Container>
  );
}
 
export default DriverLayout;