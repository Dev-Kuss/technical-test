import { useState, useEffect } from 'react';
import { 
    Dialog, DialogTitle, DialogContent, DialogActions,
    TextField, Button, FormControl, InputLabel,
    Select, MenuItem, FormControlLabel, Switch,
    SelectChangeEvent, Box, Typography
} from '@mui/material';
import { Product, PhysicalProduct, DigitalProduct } from '../types/Product';

interface ProductFormProps {
    open: boolean;
    onClose: () => void;
    onSave: (product: Omit<Product, 'id'>) => void;
    product?: Product;
}

export const ProductForm = ({ open, onClose, onSave, product }: ProductFormProps) => {
    const [type, setType] = useState<'PHYSICAL' | 'DIGITAL'>('PHYSICAL');
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [onSale, setOnSale] = useState(false);
    const [weight, setWeight] = useState('');
    const [sizeMB, setSizeMB] = useState('');

    useEffect(() => {
        if (product) {
            setType(product.product_type);
            setName(product.name);
            setPrice(product.price.toString());
            setOnSale(product.onSale);
            if ('weight' in product) {
                setWeight(product.weight.toString());
            }
            if ('sizeMB' in product) {
                setSizeMB(product.sizeMB.toString());
            }
        }
    }, [product]);

    const handleSubmit = () => {
        const baseProduct = {
            name,
            price: parseFloat(price),
            onSale
        };

        let newProduct: Omit<Product, 'id'>;
        if (type === 'PHYSICAL') {
            newProduct = {
                ...baseProduct,
                product_type: 'PHYSICAL',
                weight: parseFloat(weight)
            } as PhysicalProduct;
        } else {
            newProduct = {
                ...baseProduct,
                product_type: 'DIGITAL',
                sizeMB: parseFloat(sizeMB)
            } as DigitalProduct;
        }

        onSave(newProduct);
        handleClose();
    };

    const handleClose = () => {
        setType('PHYSICAL');
        setName('');
        setPrice('');
        setOnSale(false);
        setWeight('');
        setSizeMB('');
        onClose();
    };

    const handleTypeChange = (event: SelectChangeEvent) => {
        setType(event.target.value as 'PHYSICAL' | 'DIGITAL');
    };

    return (
        <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
            <DialogTitle>{product ? 'Edit Product' : 'New Product'}</DialogTitle>
            <DialogContent>
                <FormControl fullWidth margin="normal">
                    <InputLabel>Type</InputLabel>
                    <Select value={type} onChange={handleTypeChange}>
                        <MenuItem value="PHYSICAL">Physical Product</MenuItem>
                        <MenuItem value="DIGITAL">Digital Product</MenuItem>
                    </Select>
                </FormControl>

                <TextField
                    fullWidth
                    margin="normal"
                    label="Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Price"
                    type="number"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                />

                <Box sx={{ mt: 2, mb: 1 }}>
                    <FormControlLabel
                        control={
                            <Switch
                                checked={onSale}
                                onChange={(e) => setOnSale(e.target.checked)}
                                color="success"
                            />
                        }
                        label={
                            <Box>
                                <Typography>On Sale</Typography>
                                {onSale && (
                                    <Typography variant="caption" color="success.main">
                                        10% discount will be applied
                                    </Typography>
                                )}
                            </Box>
                        }
                    />
                </Box>

                {type === 'PHYSICAL' ? (
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Weight (kg)"
                        type="number"
                        value={weight}
                        onChange={(e) => setWeight(e.target.value)}
                    />
                ) : (
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Size (MB)"
                        type="number"
                        value={sizeMB}
                        onChange={(e) => setSizeMB(e.target.value)}
                    />
                )}
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">
                    Save
                </Button>
            </DialogActions>
        </Dialog>
    );
};
