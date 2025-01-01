import { useState } from 'react'
import { Container, AppBar, Toolbar, Typography, Button, Box } from '@mui/material'
import { ProductList } from './components/ProductList'
import { ProductForm } from './components/ProductForm'
import { Product } from './types/Product'
import { ProductService } from './services/api'

function App() {
  const [openForm, setOpenForm] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState<Product | undefined>();
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  const handleEditProduct = async (product: Product) => {
    setSelectedProduct(product);
    setOpenForm(true);
  };

  const handleSaveProduct = async (product: Omit<Product, 'id'>) => {
    try {
      if (selectedProduct?.id) {
        await ProductService.updateProduct(
          selectedProduct.id,
          { ...product, id: selectedProduct.id } as Product
        );
      } else {
        await ProductService.createProduct(product);
      }
      setOpenForm(false);
      setSelectedProduct(undefined);
      setRefreshTrigger(prev => prev + 1);
    } catch (error) {
      console.error('Error saving product:', error);
    }
  };

  return (
    <>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Product Management
          </Typography>
          <Button color="inherit" onClick={() => setOpenForm(true)}>
            Add Product
          </Button>
        </Toolbar>
      </AppBar>

      <Container>
        <Box sx={{ mt: 4 }}>
          <ProductList 
            onEdit={handleEditProduct} 
            refreshTrigger={refreshTrigger} 
            onDelete={() => setRefreshTrigger(prev => prev + 1)} 
          />
        </Box>

        <ProductForm
          open={openForm}
          onClose={() => {
            setOpenForm(false);
            setSelectedProduct(undefined);
          }}
          onSave={handleSaveProduct}
          product={selectedProduct}
        />
      </Container>
    </>
  )
}

export default App
