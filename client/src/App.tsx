import './App.css';
import { WindowComponent } from './components/WindowComponent';


function App() {
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
    </div>
  );
}

export default App;
