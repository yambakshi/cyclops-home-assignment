import './App.css';
import { Button } from '@mui/material';
import axios from 'axios';


function App() {
  const handleStaticWindowClick = async () => {
    try {
      const response = await axios.get('http://localhost:8080/StaticWindow?clientId=1');
    } catch (e) {
      console.log(e);
    }
  }

  const handleDynamicWindowClick = async () => {
    try {
      const response = await axios.get('/DynamicWindow?clientId=1');
    } catch (e) {
      console.log(e);
    }
  }


  return (
    <div className="App">
      <header className="App-header">
        <Button variant="contained" onClick={handleStaticWindowClick}>Static Window</Button>
        <Button variant="contained" onClick={handleDynamicWindowClick}>Dynamic Window</Button>
      </header>
    </div>
  );
}

export default App;
