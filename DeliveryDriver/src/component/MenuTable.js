import React, { useState } from 'react';

const MenuTable = () => {
  const [menuItems, setMenuItems] = useState([]);
  const [item_id, setItemId] = useState('');
  const [menu_name, setMenuName] = useState('');
  const [price, setPrice] = useState('');
  const [description, setDescription] = useState('');
  const [showActions, setShowActions] = useState(false);
  const [selectedItemIndex, setSelectedItemIndex] = useState(null);

  const addItem = () => {
    setMenuItems([...menuItems, { item_id, menu_name, price, description }]);
    setItemId('');
    setMenuName('');
    setPrice('');
    setDescription('');
    setShowActions(false);
  };

  const updateItem = (index) => {
    const updatedItems = [...menuItems];
    updatedItems[index] = { item_id, menu_name, price, description };
    setMenuItems(updatedItems);
    setItemId('');
    setMenuName('');
    setPrice('');
    setDescription('');
    setShowActions(false);
  };

  const deleteItem = (index) => {
    const updatedItems = [...menuItems];
    updatedItems.splice(index, 1);
    setMenuItems(updatedItems);
    setShowActions(false);
  };

  const editItem = (index) => {
    setItemId(menuItems[index].item_id);
    setMenuName(menuItems[index].menu_name);
    setPrice(menuItems[index].price);
    setDescription(menuItems[index].description);
    setSelectedItemIndex(index);
    setShowActions(true);
  };

  return (
    <div style={styles.container}>
      <h2>Menu Details</h2>
      <table style={styles.table}>
        <thead>
          <tr>
            <th style={styles.tableHeader}>Item ID</th>
            <th style={styles.tableHeader}>Menu Name</th>
            <th style={styles.tableHeader}>Price</th>
            <th style={styles.tableHeader}>Description</th>
            <th style={styles.tableHeader}>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><input type="text" value={item_id} onChange={(e) => setItemId(e.target.value)} style={styles.inputField} /></td>
            <td><input type="text" value={menu_name} onChange={(e) => setMenuName(e.target.value)} style={styles.inputField} /></td>
            <td><input type="text" value={price} onChange={(e) => setPrice(e.target.value)} style={styles.inputField} /></td>
            <td><input type="text" value={description} onChange={(e) => setDescription(e.target.value)} style={styles.inputField} /></td>
            <td>
              {!showActions && <button onClick={addItem} style={styles.addButton}>Add</button>}
              {showActions && (
                <>
                  <button onClick={() => updateItem(selectedItemIndex)} style={styles.updateButton}>Update</button>
                  <button onClick={() => deleteItem(selectedItemIndex)} style={styles.deleteButton}>Delete</button>
                </>
              )}
            </td>
          </tr>
          {menuItems.map((item, index) => (
            <tr key={index}>
              <td style={styles.tableData}>{item.item_id}</td>
              <td style={styles.tableData}>{item.menu_name}</td>
              <td style={styles.tableData}>{item.price}</td>
              <td style={styles.tableData}>{item.description}</td>
              <td><button onClick={() => editItem(index)} style={styles.editButton}>Edit</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

const styles = {
  container: {
    padding: '10px',
    border: '1px solid #ccc',
    borderRadius: '5px',
  },
  table: {
    width: '100%',
    borderCollapse: 'collapse',
  },
  tableHeader: {
    border: '1px solid #ccc',
    padding: '8px',
  },
  tableData: {
    border: '1px solid #ccc',
    padding: '8px',
  },
  inputField: {
    padding: '5px',
    border: '1px solid #ccc',
    borderRadius: '3px',
  },
  addButton: {
    backgroundColor: '#fe9e0d',
    color: 'white',
    border: 'none',
    padding: '5px 10px',
    borderRadius: '3px',
    cursor: 'pointer',
  },
  updateButton: {
    backgroundColor: '#fe9e0d',
    color: 'white',
    border: 'none',
    padding: '5px 10px',
    borderRadius: '3px',
    cursor: 'pointer',
  },
  deleteButton: {
    backgroundColor: '#fe9e0d',
    color: 'white',
    border: 'none',
    padding: '5px 10px',
    borderRadius: '3px',
    cursor: 'pointer',
  },
  editButton: {
    backgroundColor: '#fe9e0d',
    color: 'white',
    border: 'none',
    padding: '5px 10px',
    borderRadius: '3px',
    cursor: 'pointer',
  },
};

export default MenuTable;
