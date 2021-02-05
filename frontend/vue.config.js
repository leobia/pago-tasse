module.exports = {
  outputDir: 'build/dist/public',
  productionSourceMap: false,
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:5090',
        changeOrigin: true,
      },
    },
  },
};
