

export const getMessageClass = (messageType) => {
    switch (messageType) {
      case 'success':
        return 'successMessage';
      case 'error':
        return 'errorMessage';
      default:
        return 'message';  // Default to general message styling
    }
  };