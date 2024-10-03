import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

// Styled components
const MenuContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(5, 1fr); /* 5 columns */
  grid-template-rows: repeat(5, 1fr); /* 5 rows */
  gap: ${(props) => props.theme.spacing.margin}; /* Spacing from global theme */
  padding: ${(props) => props.theme.spacing.padding}; /* Padding from global theme */
  background-color: ${(props) => props.theme.colors.background}; /* Background from global theme */
  height: 100vh; /* Full viewport height */
  justify-items: center; /* Center items horizontally */
  align-items: center; /* Center items vertically */
`;

const MenuItem = styled.button`
  width: 200px;
  height: 160px;
  font-size: ${(props) => props.theme.font.size}; /* Font size from global theme */
  background-color: ${(props) => props.theme.colors.buttonBackground}; /* Button background from global theme */
  color: ${(props) => props.theme.colors.buttonText}; /* Button text color from global theme */
  border: ${(props) => props.theme.border.width} ${(props) => props.theme.border.style} ${(props) => props.theme.colors.border}; /* Border from global theme */
  border-radius: ${(props) => props.theme.spacing.borderRadius}; /* Border-radius from global theme */
  cursor: pointer;
  transition: transform 0.2s ease, background-color 0.3s ease;

  &:hover {
    background-color: ${(props) => props.theme.colors.hoverBackground}; /* Hover background from global theme */
    transform: scale(1.05);
  }

  &:active {
    transform: scale(0.95);
  }
`;

const Menu = () => {
  const navigate = useNavigate();

  const handleNavigation = (option) => {
    navigate(`/option${option}`);
  };

  return (
    <MenuContainer>
      {Array.from({ length: 25 }, (_, index) => (
        <MenuItem
          key={index}
          onClick={() => handleNavigation(index + 1)}
        >
          Option {index + 1}
        </MenuItem>
      ))}
    </MenuContainer>
  );
};

export default Menu;
