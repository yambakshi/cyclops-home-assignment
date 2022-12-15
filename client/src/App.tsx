import { Button } from '@mui/material';
import axios from 'axios';
import './App.css';
import { WindowComponent } from './components/WindowComponent';


function App() {
  const exitEndpoint = 'http://localhost:8080/exit';
  const windowsConfigs = {
    static: {
      name: 'Static Window',
      endpoint: 'http://localhost:8080/StaticWindow'
    },
    dynamic: {
      name: 'Dynamic Window',
      endpoint: 'http://localhost:8080/DynamicWindow'
    }
  }

  return (
    <div className="app-container">
      <div className="windows-container">
        <WindowComponent config={windowsConfigs.static}></WindowComponent>
        <WindowComponent config={windowsConfigs.dynamic}></WindowComponent>
      </div>
      <Button variant="contained" onClick={() => { axios.get(exitEndpoint) }} style={{
        fontSize: '30px',
        color: '#fff',
        backgroundColor: 'red',
        marginBottom: '20px',
        width: '100px',
        alignSelf: 'center'
      }}>
        Exit
      </Button>
    </div>
  );
}

export default App;
