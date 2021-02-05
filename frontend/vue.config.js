module.exports = {
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:5090',
        changeOrigin: true,
      },
    },
  },
};
