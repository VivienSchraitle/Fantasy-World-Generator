import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/index.css'; // Import CSS module

const Menu = () => {
  const navigate = useNavigate();

  const handleButtonClick = (path) => {
    navigate(path);
  };

  return (
    <div className="menuContainer">
      <button className="button" onClick={() => handleButtonClick('/faction-generator')}>Faction Generator</button>
      <button className="button" onClick={() => handleButtonClick('/page2')}>Page 2</button>
      <button className="button" onClick={() => handleButtonClick('/page3')}>Page 3</button>
      <button className="button" onClick={() => handleButtonClick('/page4')}>Page 4</button>
      <button className="button" onClick={() => handleButtonClick('/page5')}>Page 5</button>
      <button className="button" onClick={() => handleButtonClick('/page6')}>Page 6</button>
      <button className="button" onClick={() => handleButtonClick('/page7')}>Page 7</button>
      <button className="button" onClick={() => handleButtonClick('/page8')}>Page 8</button>
      <button className="button" onClick={() => handleButtonClick('/page9')}>Page 9</button>
      <button className="button" onClick={() => handleButtonClick('/page10')}>Page 10</button>
      <button className="button" onClick={() => handleButtonClick('/page11')}>Page 11</button>
      <button className="button" onClick={() => handleButtonClick('/page12')}>Page 12</button>
      <button className="button" onClick={() => handleButtonClick('/page13')}>Page 13</button>
      <button className="button" onClick={() => handleButtonClick('/page14')}>Page 14</button>
      <button className="button" onClick={() => handleButtonClick('/page15')}>Page 15</button>
      <button className="button" onClick={() => handleButtonClick('/page16')}>Page 16</button>
      <button className="button" onClick={() => handleButtonClick('/page17')}>Page 17</button>
      <button className="button" onClick={() => handleButtonClick('/page18')}>Page 18</button>
      <button className="button" onClick={() => handleButtonClick('/page19')}>Page 19</button>
      <button className="button" onClick={() => handleButtonClick('/page20')}>Page 20</button>
      <button className="button" onClick={() => handleButtonClick('/page21')}>Page 21</button>
      <button className="button" onClick={() => handleButtonClick('/page22')}>Page 22</button>
      <button className="button" onClick={() => handleButtonClick('/page23')}>Page 23</button>
      <button className="button" onClick={() => handleButtonClick('/page24')}>Page 24</button>
      <button className="button" onClick={() => handleButtonClick('/page25')}>Page 25</button>
    </div>
  );
};

export default Menu;