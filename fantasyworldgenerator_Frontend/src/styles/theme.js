// theme.js
export const theme = {
  colors: {
    background: '#f5f5f5',       // Light gray background for modern feel
    primary: '#6A5ACD',          // Soft Slate Blue for primary actions
    secondary: '#FF6F61',        // Soft Coral for secondary buttons
    buttonBackground: '#6A5ACD', // Matching primary button background
    buttonText: '#FFFFFF',       // Clean white for button text
    text: '#2E2E2E',             // Darker text color for better readability
    border: '#E0E0E0',           // Light border to keep a minimal feel
    hoverBackground: '#5A4FCF',  // Slightly darker hover for buttons
    error: '#FF4040',            // Error color for form validation
    success: '#4CAF50',          // Success green for positive actions/messages
  },
  font: {
    family: "'Roboto', sans-serif",  // Modern sans-serif font for a cleaner look
    size: '18px',                    // Larger default font size for better readability
    weight: '400',                   // Normal font weight
    headingWeight: '600',            // Heavier weight for headings
  },
  spacing: {
    padding: '12px',                 // Increased padding for more breathing space
    margin: '12px',                  // Increased margin for better spacing between elements
    borderRadius: '8px',             // Slightly more rounded borders for a modern look
  },
  border: {
    width: '1px',
    style: 'solid',
  },
  shadows: {
    default: '0 4px 6px rgba(0, 0, 0, 0.1)',  // Soft shadow for cards, buttons, etc.
    hover: '0 6px 8px rgba(0, 0, 0, 0.15)',   // Slightly stronger shadow on hover
  },
};
