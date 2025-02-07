export const colors = {
    primary: '#547AA5',
    secondary: '#87CEEB',
    background: '#F0F8FF',
    text: '#333333',
    success: '#00A676',
    error: '#FF4500'
  };
  
  export const styles = {
    container: {
      maxWidth: '1200px',
      margin: '0 auto',
      padding: '20px',
      backgroundColor: colors.background,
      borderRadius: '8px',
      boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)'
    },
    form: {
      marginBottom: '2rem'
    },
    table: {
      width: '100%',
      marginBottom: '1rem',
      color: colors.text,
      backgroundColor: '#FFFFFF',
      borderRadius: '8px',
      overflow: 'hidden',
      boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)'
    },
    tableHeader: {
      backgroundColor: colors.primary,
      color: '#FFFFFF'
    },
    tableRow: {
      '&:nth-of-type(even)': {
        backgroundColor: '#F9F9F9'
      }
    },
    button: {
      backgroundColor: colors.primary,
      color: '#FFFFFF',
      border: 'none',
      padding: '10px 20px',
      borderRadius: '5px',
      cursor: 'pointer',
      '&:hover': {
        backgroundColor: '#007BFF'
      }
    },
    alert: {
      padding: '10px',
      borderRadius: '5px',
      marginBottom: '1rem',
      display: 'flex',
      alignItems: 'center',
      gap: '10px'
    },
    alertSuccess: {
      backgroundColor: colors.success,
      color: '#FFFFFF'
    },
    alertError: {
      backgroundColor: colors.error,
      color: '#FFFFFF'
    }
  };