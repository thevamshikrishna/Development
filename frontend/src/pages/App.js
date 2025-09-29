import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import AddStudent from './pages/AddStudent';
import ViewStudent from './pages/ViewStudent';

function App() {
  return (
    <div>
      <nav style={{ padding: '10px', background: '#eee' }}>
        <Link to="/" style={{ marginRight: '10px' }}>Home</Link>
        <Link to="/add-student" style={{ marginRight: '10px' }}>Add Student</Link>
        <Link to="/view-student">View Students</Link>
      </nav>

      <div style={{ padding: '20px' }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/add-student" element={<AddStudent />} />
          <Route path="/view-student" element={<ViewStudent />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;