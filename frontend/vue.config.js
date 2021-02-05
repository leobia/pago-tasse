module.exports = {
  outputDir: 'build/dist/public',
  productionSourceMap: false,
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:5000',
        changeOrigin: true,
      },
    },
  },
};
