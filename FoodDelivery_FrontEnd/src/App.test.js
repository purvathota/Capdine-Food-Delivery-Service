import MenuCard from "./components/menuCard";
import MenuFooter from "./components/menuFooter";
 
 
test('renders food item correctly', () => {
  const { getByText } = (<MenuCard name="Burger" price={9.99} />);
 
});
test('renders food item correctly', () => {
  const { getByText } = (<MenuCard name="Pizza" price={12.99} />);
 
});
test('renders food item correctly', () => {
  const { getByText } = (<MenuCard name="Pasta" price={10.99} />);
 
});
test('renders food item correctly', () => {
  const { getByText } = (<MenuCard name="Chicken Tikka Masala" price={14.99} />);
 
});
test('renders phone number', () => {
  const { getByText } = (<MenuFooter Phone="999-444-4442"/>);
 
});
test('renders address', () => {
  const { getByText } = (<MenuFooter Address="1234" />);
});