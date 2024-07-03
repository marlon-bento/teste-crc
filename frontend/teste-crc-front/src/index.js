import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

import Home from './components/Home';
import { BrowserRouter, Routes, Route } from 'react-router-dom';import Header from './components/Header';
import Eventos from './components/Eventos';
import Participantes from './components/Participantes';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      
      <BrowserRouter>
          <Header/>
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/eventos' element={<Eventos/>} />
            <Route path='/participantes' element={<Participantes/>} />
          </Routes>
      </BrowserRouter>
    
  </React.StrictMode>
);
