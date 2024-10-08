import { createTheme } from 'styled-components';

const baseTheme = createTheme({
  typography: {
    fontFamily: 'Roboto, sans-serif',
    fontSize: 14,
    fontWeightRegular: 400,
    fontWeightMedium: 500,
    fontWeightBold: 700,
    h1: {
      fontSize: 32,
      fontWeight: 'bold',
    },
    h2: {
      fontSize: 28,
      fontWeight: 'medium',
    },
    h3: {
      fontSize: 24,
      fontWeight: 'regular',
    },
    h4: {
      fontSize: 20,
      fontWeight: 'regular',
    },
    body1: {
      fontSize: 16,
      lineHeight: 1.5,
    },
    body2: {
      fontSize: 14,
      lineHeight: 1.4,
    },
  },
  palette: {
    primary: {
      main: '#007bff',
      light: '#66b1ff',
      dark: '#004080',
      contrastText: '#fff',
    },
    secondary: {
      main: '#ff4081',
      light: '#ff80c4',
      dark: '#cc0052',
      contrastText: '#fff',
    },
    error: {
      main: '#d32f2f',
      light: '#ff6b6b',
      dark: '#9a0007',
      contrastText: '#fff',
    },
    warning: {
      main: '#ff9800',
      light: '#ffa726',
      dark: '#cc6600',
      contrastText: '#fff',
    },
    info: {
      main: '#03a9f4',
      light: '#63ceff',
      dark: '#006db6',
      contrastText: '#fff',
    },
    success: {
      main: '#4caf50',
      light: '#81c784',
      dark: '#00893d',
      contrastText: '#fff',
    },
    text: {
      primary: '#000',
      secondary: '#666',
      disabled: '#ccc',
    },
    background: {
      default: '#f5f5f5',
      paper: '#fff',
    },
    action: {
      disabledBackground: '#f5f5f5',
      disabledOpacity: 0.38,
    },
  },
  spacing: 8, // Base spacing unit
  breakpoints: {
    values: {
      xs: 0,
      sm: 600,
      md: 900,
      lg: 1200,
      xl: 1536,
    },
  },
});

export default baseTheme;