/**
 * @author:     zeddic
 * @description:  主页
 * @date:    2024/7/12 上午10:20
 */

import { CssBaseline, ThemeProvider } from '@mui/material';
import { createTheme } from '@mui/material/styles';
import React from 'react';
import type { AppProps } from 'next/app';

const theme = createTheme();

function MyApp({ Component, pageProps }: AppProps) {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline />
            <Component {...pageProps} />
        </ThemeProvider>
    );
}

export default MyApp;
